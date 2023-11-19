import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jesus.moviesskell.data.response.MovieData
import com.jesus.moviesskell.network.services.MoviesApiService


private const val TAG = "MoviesPagerSource"
class MoviesPagerSource(
    private val moviesApiService: MoviesApiService
) :
    PagingSource<Int, MovieData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        return try {
            val pageNumber = params.key ?: 1
            val response = moviesApiService.getMovies(pageNumber)

            val prevKey = if (pageNumber == 1) null else (pageNumber - 1)
            val nextKey = if (pageNumber == response.totalPages) null else (pageNumber + 1)

            LoadResult.Page(
                data = response.results,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            Log.i(TAG, "load: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}