package com.ronvel.farztev.admin.component;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.ronvel.farztev.admin.controller.dto.PublishType;

public class ThumbnailGenerator {

	private final String host;
	private final String username;
	private final String password;
	private final String environmentUrl;
	private final int width;
	private final int height;
	private final PublishType publishType;
	
	public ThumbnailGenerator(String host, String username, String password, String environmentUrl, int width,
			int height, PublishType publishType) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.environmentUrl = environmentUrl;
		this.width = width;
		this.height = height;
		this.publishType = publishType;
	}

	public void generateThumbnails() throws SocketException, IOException {
		FTPClient client = new FTPClient();
		FTPClient client2 = new FTPClient();
		client = connectClient();
		client2 = connectClient();
		client.setFileType(FTP.BINARY_FILE_TYPE);
		client2.setFileType(FTP.BINARY_FILE_TYPE);

		client.changeWorkingDirectory("farztev_test");
		client.changeWorkingDirectory("images");
		client2.changeWorkingDirectory("farztev_test");
		client2.changeWorkingDirectory("images");
		client2.changeWorkingDirectory("thumbnails");
		client2.changeWorkingDirectory(String.valueOf(width + "x" + height));

		generateThumbails(client, client2, "", "");
		
		client.logout();
		client.disconnect();
	}
	
	private FTPClient connectClient() throws SocketException, IOException {
		FTPClient client = new FTPClient();

		client.connect(host);
		int reply = client.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
        	client.disconnect();
            throw new RuntimeException("Exception in connecting to FTP Server");
        }
//		client.setFileType(FTP.ASCII_FILE_TYPE);
//		client.setControlEncoding("UTF-8");
		client.login(username, password);
		client.enterLocalPassiveMode(); 
		client.setControlKeepAliveTimeout(300); // set timeout to 5 minutes
		client.setSoTimeout(300000);
		return client;
	}
	
	private void generateThumbails(FTPClient client,FTPClient client2, String directory, String absolutePath) throws IOException {
		System.out.println("Directory : " + directory);
		if (!directory.isEmpty()) {
			client.changeWorkingDirectory(directory);
			if (client2.makeDirectory(directory)) {
				System.out.println("Directory has been created : " + absolutePath);
			}
			client2.changeWorkingDirectory(directory);
		}
		
		FTPFile[] files = client.listFiles();
		for(FTPFile file : files) {
			String fileName = file.getName();
			if(file.isFile()) {
				String filePath = absolutePath + "/" + fileName;
				String fileToCopy = filePath.substring(1, filePath.length());

				if (publishType != PublishType.IMAGE_FORCE && client2.listFiles(fileName).length > 0) {
					System.out.println("Skip scaling  : " + fileToCopy);
					continue;
				}
				
				scale(fileToCopy);
				System.out.println("File : " + fileToCopy);
				System.out.println("Thumbnail generated : " + fileToCopy);
			}
		}
		
		FTPFile[] subDirectories = client.listDirectories();
		for(FTPFile subDirectory : subDirectories) {
			String subDirectoryName = subDirectory.getName();
			if(!(".".equals(subDirectoryName) || "..".equals(subDirectoryName) || "thumbnails".equals(subDirectoryName))) {
				generateThumbails(client, client2, subDirectoryName, absolutePath + "/" + subDirectoryName);
			}
		}
		client.changeWorkingDirectory("..");
		client2.changeWorkingDirectory("..");
	}
	
	private void scale(String absolutePath) throws ClientProtocolException, IOException {
		String url = environmentUrl + "/images/scaler.php?filename=" + absolutePath+"&width=" + width + "&height=" + height;
		System.out.println(url);
		HttpGet request = new HttpGet(url);
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }
	}
}
