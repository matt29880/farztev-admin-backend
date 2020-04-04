<p>
	> <a href="../">Farzteo.com</a>
</p>

<h1>{{{trip.name}}}</h1>

<h2 class="trip-articles-title">Articles</h2>

<section class="articles">
	<div class="row">
	{{#articles}}
		<div class="col-3 col-s-3 article">
			<a href="../articles/{{id}}.html">
				<h2>
					{{{name}}}
				</h2>
			  	<div class="thumbnail">
					<img src="../images/thumbnails/300x300{{thumbnailUrl}}" />
			  	</div>
			</a>
		</div>
	{{/articles}}
	</div>
</section>

<h2>Albums</h2>

<section class="albums">
	<div class="row">
	{{#albums}}
		<div class="col-3 col-s-3 album">
			<a href="../albums/{{id}}.html">
				<h2>
					{{{name}}}
				</h2>
		 	 	<div class="thumbnail">
					<img src="../images/thumbnails/300x300{{thumbnailUrl}}" width="200" />
			  	</div>
			</a>
		</div>
	{{/albums}}
	</div>
</section>