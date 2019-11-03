<h1>Nos voyages</h1>

<section class="timeline">
	<div class="row">
	{{#timelines}}
		<div class="col-3 col-s-3 container">
			
			<div class="timeline-item">
				<div class="date">{{start}} - {{end}}</div>
				<div class="timeline-title">
					<a href="./trips/{{id}}.html">
						<img src="./images/thumbnails/300x300{{image}}" />
						<h2>
								{{{title}}}
						</h2>
					</a>
				</div>
				<div class="timeline-content">
					<p>{{{summary}}}</p>
				</div>
			</div>
			
		</div>
	{{/timelines}}
	</div>
</section>