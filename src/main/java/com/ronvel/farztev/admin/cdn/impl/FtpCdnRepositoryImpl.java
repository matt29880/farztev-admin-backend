package com.ronvel.farztev.admin.cdn.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ronvel.farztev.admin.cdn.CdnRepository;
import com.ronvel.farztev.admin.cdn.FileDetail;

@Repository
public class FtpCdnRepositoryImpl implements CdnRepository {

	private final String mediaFolder;
	private final String host;
	private final String username;
	private final String password;
	private final String environmentSuffix;
	
	public FtpCdnRepositoryImpl(
			@Value("${application.media.photo.location}") String mediaFolder, 
			@Value("${application.ftp.host}") String host,
			@Value("${application.ftp.username}") String username,
			@Value("${application.ftp.password}") String password,
			@Value("${application.environment.suffix}") String environmentSuffix) {
		this.mediaFolder = mediaFolder;
		this.host = host;
		this.username = username;
		this.password = password;
		this.environmentSuffix = environmentSuffix;
	}

	@Override
	public List<FileDetail> list(String folderPath) throws IOException {
		String absolutePath = mediaFolder + folderPath;
		return listFilesFromFtp(absolutePath, host, username, password).stream()
				.filter(f -> !(".".equals(f.getName()) || "..".equals(f.getName())))
				.map(file -> mapToFileDetail(folderPath, file))
				.collect(Collectors.toList());
	}

	private FileDetail mapToFileDetail(String parentPath, FTPFile file) {
		return FileDetail.builder()
				.name(file.getName())
				.path(createPath(parentPath, file))
				.isDirectory(file.isDirectory())
				.build();
	}

	private String createPath(String parentPath, FTPFile file) {
	  	if (parentPath.equals(File.separator)) {
    		return parentPath + file.getName();
    	} else {
    		return parentPath + File.separator + file.getName();
    	}
  	}

	public List<FTPFile> listFilesFromFtp(String folderPath, String host, String username, String password)
			throws IOException {
		FTPClient client = new FTPClient();
		FileInputStream fis = null;

		try {
			client.connect(host);
			int reply = client.getReplyCode();
	        if (!FTPReply.isPositiveCompletion(reply)) {
	        	client.disconnect();
	            throw new RuntimeException("Exception in connecting to FTP Server");
	        }
			client.login(username, password);
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.enterLocalPassiveMode();

			String folder = "farztev_" + environmentSuffix + "/" + folderPath;
			boolean res = client.changeWorkingDirectory(folder);
			System.out.println("chg " + folder);

			if(!"/".equals(folderPath)) {
				client.changeWorkingDirectory(folderPath);
			}
			FTPFile[] files = client.listFiles();
			client.logout();
			return Arrays.asList(files);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
