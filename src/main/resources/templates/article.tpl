<p>
	> <a href="../">Farzteo.com</a> > <a href="../trips/{{trip.id}}.html">Trip : {{trip.name}}</a>
</p>
<div class="article">
	<h1>{{{name}}}</h1>
	<div class="row">
	  <div class="col-12 col-s-12">
		<img src="../images/thumbnails/300x300{{thumbnailUrl}}" />
	  </div>
	</div>
	
	<div class="descriptions">
		{{#descriptions}}
			{{{article-description this}}}
		{{/descriptions}}
	</div>
</div>