import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jesus.moviesskell.data.response.MovieData
import com.jesus.moviesskell.data.response.toDomainModel
import com.jesus.moviesskell.domain.models.movies.Movie
import com.jesus.moviesskell.network.services.MoviesApiService


private const val TAG = "MoviesPagerSource"

class MoviesPagerSource(
    private val moviesApiService: MoviesApiService
) :
    PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val response = moviesApiService.getMovies(pageNumber)

            val prevKey = if (pageNumber == 1) null else (pageNumber - 1)
            val nextKey = if (pageNumber == response.totalPages) null else (pageNumber + 1)

            val movies = response.results.map { it.toDomainModel() }

            LoadResult.Page(
                data = movies,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            Log.i(TAG, "load: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}