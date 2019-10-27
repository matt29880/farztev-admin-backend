<h1>Trip</h1>

<section class="trips">
	<div class="trip">
		<h2>{{{trip.name}}}</h2>
		<img src="../images{{trip.thumbnailUrl}}" />
		<p>{{{trip.summary}}}</p>
	</div>
</section>

<h2>Articles</h2>

<section class="articles">
	{{#articles}}
		<div class="article">
			<h2>{{{name}}}</h2>
			<img src="../images{{thumbnailUrl}}" />
		</div>
	{{/articles}}
</section>

<h2>Albums</h2>

<section class="albums">
	{{#albums}}
		<div class="album">
			<h2>{{name}}</h2>
			<img src="../images{{thumbnailUrl}}" />
		</div>
	{{/albums}}
</section>