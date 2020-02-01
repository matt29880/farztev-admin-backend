<h1>Nos voyages</h1>
<div class="timeline">
  {{#timelines}}
  <div class="container left">
    <div class="content">
      <h2>
		<a href="./trips/{{id}}.html">
      		{{{title}}}
      	</a>
      </h2>
      <p>
		<a href="./trips/{{id}}.html">
      		<img src="./images/thumbnails/450x450{{image}}"/>
      	</a>
      </p>
    </div>	
    <div class="date">
	<p class="day">12</p>
	<p class="month">Mai</p>
	<p class="year">2019</p>
    </div>
  </div>
  {{/timelines}}
</div>