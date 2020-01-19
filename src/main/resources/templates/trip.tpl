<p>
	> <a href="../">Farzteo.com</a>
</p>
<section class="trips">
	<h1>{{{trip.name}}}</h1>
	<div class="row">
		<div class="col-6 col-s-6 trip">
			  <div class="thumbnail">
					<picture>
					  <source media="(min-width: 600px)" srcset="../images/thumbnails/600x600{{trip.thumbnailUrl}}">
					  <img src="../images/thumbnails/300x300{{trip.thumbnailUrl}}" 
					  		alt="Flowers" style="width:auto;">
					</picture>
			  </div>
		</div>
		<div class="col-3 col-s-3 trip">
			<p>{{{trip.summary}}}</p>
		</div>
	</div>
</section>

<h2>Articles</h2>

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
					<img src="../images/thumbnails/300x300{{thumbnailUrl}}" />
			  	</div>
			</a>
		</div>
	{{/albums}}
	</div>
</section>