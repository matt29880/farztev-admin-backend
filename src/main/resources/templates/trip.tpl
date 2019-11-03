<section class="trips">
	<div class="trip">
		<h1>{{{trip.name}}}</h1>
		
		<div class="row">
		  <div class="col-12 col-s-12 thumbnail">
				<img src="../images/thumbnails/300{{trip.thumbnailUrl}}" />
		  </div>
		</div>
		<p>{{{trip.summary}}}</p>
	</div>
</section>

<h2>Articles</h2>

<section class="articles">
	{{#articles}}
		<div class="article">
			<h2>
				<a href="../articles/{{id}}.html">
					{{{name}}}
				</a>
			</h2>
			<div class="row">
			  <div class="col-12 col-s-12 thumbnail">
				<img src="../images/thumbnails/300{{thumbnailUrl}}" />
			  </div>
			</div>
		</div>
	{{/articles}}
</section>

<h2>Albums</h2>

<section class="albums">
	{{#albums}}
		<div class="album">
			<h2>
				<a href="../albums/{{id}}.html">
					{{{name}}}
				</a>
			</h2>
			<div class="row">
			  <div class="col-12 col-s-12 thumbnail">
				<img src="../images/thumbnails/300{{thumbnailUrl}}" />
			  </div>
			</div>
		</div>
	{{/albums}}
</section>