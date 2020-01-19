<h1>Nos voyages</h1>

<section class="timeline">
	<div class="row">
	{{#timelines}}
		<div class="timeline-item">
			<div class="col-6 col-s-3 container">
				<div class="date">{{{periodDescription}}}</div>
				<div class="timeline-title">
					<a href="./trips/{{id}}.html">
						<img src="./images/thumbnails/450x450{{image}}" />
					</a>
				</div>
			</div>
			<div class="col-6 col-s-3 container">
				<h2>{{{title}}}</h2>
				<div class="timeline-content">
					<p>{{{summary}}}</p>
				</div>
			</div>
		</div>
	{{/timelines}}
	</div>
</section>