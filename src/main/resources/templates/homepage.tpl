<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta name="robots" content="noindex">
		<link rel="stylesheet" type="text/css" href="styles.css">
        <title>Site d'un couple breton passionné de voyage</title>
		<link rel="icon" href="farztev.png" />
	</head>
	<body>
		<header>
			<h1><a href="http://www.farzteo.com">Farzteo</a></h1>
			<h2>Site d'un couple breton passionn&eacute; de voyage</h2>
			<div id="banner">
				<img src="banner2.jpg" />
			</div>
		</header>
		<h1>Nos voyages</h1>

		{{#timelines}}
		<section class="timeline">
			<div class="container">
 				
				<div class="timeline-item">
					<div class="date">{{start}} - {{end}}</div>
					<div class="timeline-title">
						<img src=".{{image}}" />
						<h2>{{{title}}}</h2>
					</div>
					<div class="timeline-content">
						<p>{{{summary}}}</p>
					</div>
				</div>
 				
			</div>
		</section>
		{{/timelines}}
	</body>
</html>