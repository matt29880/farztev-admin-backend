<html>
	<head>
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
						<h2>{{title}}</h2>
						<div class="date">from {{from}} to {{to}}</div>
						<p>{{{summary}}}</p>
						<a class="bnt-more" href="javascript:void(0)">More</a>
					</div>
				</div>
 				{{/timelines}}
			</div>
		</section>
	</body>
</html>