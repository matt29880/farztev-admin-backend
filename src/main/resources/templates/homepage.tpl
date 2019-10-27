<h1>Nos voyages</h1>

{{#timelines}}
<section class="timeline">
	<div class="container">
		
		<div class="timeline-item">
			<div class="date">{{start}} - {{end}}</div>
			<div class="timeline-title">
				<img src="./images{{image}}" />
				<h2>
					<a href="./trips/{{id}}.html">
						{{{title}}}
					</a>
				</h2>
			</div>
			<div class="timeline-content">
				<p>{{{summary}}}</p>
			</div>
		</div>
		
	</div>
</section>
{{/timelines}}