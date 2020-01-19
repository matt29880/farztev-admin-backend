<p>
	> <a href="../">Farzteo.com</a> > <a href="../trips/{{trip.id}}.html">Trip : {{trip.name}}</a>
</p>
<h1>{{{name}}}</h1>
<div class="row">
  <div class="col-12 col-s-12">
	<img src="../images/thumbnails/300x300{{thumbnailUrl}}" />
  </div>
</div>

<div class="row">
{{#images}}
  <div class="col-3 col-s-3">
  	<a href="../images{{url}}">
		<img src="../images/thumbnails/300x300{{url}}" />
  	</a>
  </div>
{{/images}}
</div>