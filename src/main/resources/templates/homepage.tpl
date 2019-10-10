<html>
	<head>
		<meta name="robots" content="noindex">
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
		<header>
			<h1>{{title}}</h1>
		</header>
		<section class="timeline">
			<div class="container">
 				{{#timelines}}
				<div class="timeline-item">
					<div class="timeline-content">
						<h2>{{{title}}}</h2>
						<div class="date">{{date}}</div>
						<p>{{{summary}}}</p>
						<img src=".{{image}}" width="200"/>
						<a class="bnt-more" href="javascript:void(0)">More</a>
					</div>
				</div>
 				{{/timelines}}
			</div>
		</section>
	</body>
</html>