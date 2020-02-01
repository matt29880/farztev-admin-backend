<h1>Nos voyages</h1>
<div class="timeline">
  {{#timelines}}
  <div class="container {{side}}">
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
	<p class="day">{{day}}</p>
	<p class="month">{{month}}</p>
	<p class="year">{{year}}</p>
    </div>
  </div>
  {{/timelines}}
</div>