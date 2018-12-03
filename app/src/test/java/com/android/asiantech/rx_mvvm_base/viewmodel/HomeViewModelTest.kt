package com.android.asiantech.rx_mvvm_base.viewmodel

import com.android.asiantech.rx_mvvm_base.BaseTest
import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.HomeResponse
import com.android.asiantech.rx_mvvm_base.ui.main.home.HomeViewModel
import com.android.asiantech.rx_mvvm_base.util.RxSchedulersOverrideRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.PublishSubject
import org.hamcrest.CoreMatchers
import org.hamcrest.core.Is.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt
import org.mockito.MockitoAnnotations

/**
 *
 * @author at-haingo
 */
@Suppress("IllegalIdentifier")
class HomeViewModelTest : BaseTest() {
    @get:Rule
    val rule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `Given nothing - When call constructor HomeViewModel - Then has no exception`() {
        /* Given */

        /* When */

        /* Then */
        Assert.assertThat(viewModel, CoreMatchers.notNullValue())
    }

    @Test
    fun `Given nothing - When call getComicsFromServer() - Then return right list comic`() {
        /* Given */
        val test = TestObserver<MutableList<Comic>>()
        `when`(repository.getComics(1)).thenReturn(Single.just(HomeResponse(true,
                mutableListOf(Comic(1, "jindo", "football comic", "author", 100, true, 100, "url1"),
                        Comic(2, "jindodinho", "football comic", "author", 50, true, 100, "url2")))))
        viewModel.getComicsObservable().subscribe(test)

        /* When */
        viewModel.getComicsFromServer()

        /* Then */
        test.assertValue {
            Assert.assertThat(it.size, `is`(2))
            Assert.assertThat(it[0].id, `is`(1))
            Assert.assertThat(it[1].id, `is`(2))
            true
        }
    }

    @Test
    fun `Given comic list and a position in list - When call favorite() - Then return right FavoriteResponse`() {
        /* Given */
        val test = TestObserver<FavoriteResponse>()
        viewModel.getComics().add(Comic(1, "jindo", "football comic", "author", 100, true, 100, "url1"))
        `when`(repository.favorite(1)).thenReturn(Single.just(FavoriteResponse(true)))

        /* When */
        viewModel.favorite(0).subscribe(test)

        /* Then */
        test.assertValue {
            Assert.assertThat(it.success, `is`(true))
            true
        }
    }

    @Test
    fun `Given comic list and a position in list - When call unFavorite() - Then return right FavoriteResponse`() {
        /* Given */
        val test = TestObserver<FavoriteResponse>()
        viewModel.getComics().add(Comic(1, "jindo", "football comic", "author", 100, true, 100, "url1"))
        `when`(repository.unFavorite(1)).thenReturn(Single.just(FavoriteResponse(true)))

        /* When */
        viewModel.unFavorite(0).subscribe(test)

        /* Then */
        test.assertValue {
            Assert.assertThat(it.success, `is`(true))
            true
        }
    }

    @Test
    fun `Given comic list and a postion in list - When call isFavorite() - Then the result should be correct`() {
        /* Given */
        viewModel.getComics().add(Comic(1, "jindo", "football comic", "author", 100, true, 100, "url1"))

        /* When */


        /* Then */
        Assert.assertThat(viewModel.isFavorite(0), `is`(true))
    }

    @Test
    fun `Given isLoading is false, nextPageFlag is true and total of visibleItemCount, firstVisibleItem and VISIBLE_THRESHOLD more than or similar totalItemCount - When call loadMore() - Then call getComicFromServer()`() {
        /* Given */
        val visibleItemCount = 10
        val firstVisibleItem = 10
        val totalItemCount = 20
        `when`(repository.getComics(anyInt())).thenReturn(Single.just(HomeResponse(true,
                mutableListOf(Comic(1, "jindo", "football comic", "author", 100, true, 100, "url1"),
                        Comic(2, "jindodinho", "football comic", "author", 50, true, 100, "url2")))))
        /* When */
        viewModel.getComicsFromServer()
        viewModel.loadMore(visibleItemCount, totalItemCount, firstVisibleItem)

        /* Then */
        Assert.assertThat(viewModel.getComics().size, `is`(4))
    }

    @Test
    fun `Given isLoading is true, nextPageFlag is true and total of visibleItemCount, firstVisibleItem and VISIBLE_THRESHOLD more than or similar totalItemCount - When call loadMore() - Then call getComicFromServer()`() {
        /* Given */
        val visibleItemCount = 10
        val firstVisibleItem = 10
        val totalItemCount = 20
        val single = Single.just(HomeResponse(true,
                mutableListOf(Comic(1, "jindo", "football comic", "author", 100, true, 100, "url1"),
                        Comic(2, "jindodinho", "football comic", "author", 50, true, 100, "url2"))))
        val delayer = PublishSubject.create<Boolean>()
        `when`(repository.getComics(anyInt())).thenReturn(single)

        /* When */
        viewModel.getComicsFromServer()
        `when`(repository.getComics(anyInt())).thenReturn(single.delaySubscription(delayer))
        viewModel.getComicsFromServer()
        viewModel.loadMore(visibleItemCount, totalItemCount, firstVisibleItem)

        /* Then */
        Assert.assertThat(viewModel.getComics().size, `is`(2))
    }

    @Test
    fun `Given isLoading is false, nextPageFlag is false and total of visibleItemCount, firstVisibleItem and VISIBLE_THRESHOLD more than or similar totalItemCount - When call loadMore() - Then call getComicFromServer()`() {
        /* Given */
        val visibleItemCount = 10
        val firstVisibleItem = 10
        val totalItemCount = 20
        `when`(repository.getComics(anyInt())).thenReturn(Single.just(HomeResponse(false,
                mutableListOf(Comic(1, "jindo", "football comic", "author", 100, true, 100, "url1"),
                        Comic(2, "jindodinho", "football comic", "author", 50, true, 100, "url2")))))
        /* When */
        viewModel.getComicsFromServer()
        viewModel.loadMore(visibleItemCount, totalItemCount, firstVisibleItem)

        /* Then */
        Assert.assertThat(viewModel.getComics().size, `is`(2))
    }

    @Test
    fun `Given isLoading is true, nextPageFlag is false and total of visibleItemCount, firstVisibleItem and VISIBLE_THRESHOLD more than or similar totalItemCount - When call loadMore() - Then call getComicFromServer()`() {
        /* Given */
        val progressDialogTest = TestObserver<Boolean>()
        val visibleItemCount = 10
        val firstVisibleItem = 10
        val totalItemCount = 20
        val single = Single.just(HomeResponse(false,
                mutableListOf(Comic(1, "jindo", "football comic", "author", 100, true, 100, "url1"),
                        Comic(2, "jindodinho", "football comic", "author", 50, true, 100, "url2"))))
        val delayer = PublishSubject.create<Boolean>()
        `when`(repository.getComics(anyInt())).thenReturn(single)

        /* When */
        viewModel.updateProgressDialogStatus().subscribe(progressDialogTest)
        viewModel.getComicsFromServer()
        `when`(repository.getComics(anyInt())).thenReturn(single.delaySubscription(delayer))
        viewModel.getComicsFromServer()
        viewModel.loadMore(visibleItemCount, totalItemCount, firstVisibleItem)

        /* Then */
        Assert.assertThat(viewModel.getComics().size, `is`(2))
        progressDialogTest.assertValueCount(3)
        progressDialogTest.assertValueAt(0) {
            Assert.assertThat(it, `is`(true))
            true
        }
        progressDialogTest.assertValueAt(1) {
            Assert.assertThat(it, `is`(false))
            true
        }
        progressDialogTest.assertValueAt(2) {
            Assert.assertThat(it, `is`(true))
            true
        }
    }

    @Test
    fun `Given isLoading is true, nextPageFlag is false and total of visibleItemCount, firstVisibleItem and VISIBLE_THRESHOLD less than or similar totalItemCount - When call loadMore() - Then call getComicFromServer()`() {
        /* Given */
        val progressDialogTest = TestObserver<Boolean>()
        val visibleItemCount = 10
        val firstVisibleItem = 10
        val totalItemCount = 50
        val single = Single.just(HomeResponse(false,
                mutableListOf(Comic(1, "jindo", "football comic", "author", 100, true, 100, "url1"),
                        Comic(2, "jindodinho", "football comic", "author", 50, true, 100, "url2"))))
        val delayer = PublishSubject.create<Boolean>()
        `when`(repository.getComics(anyInt())).thenReturn(single)

        /* When */
        viewModel.updateProgressDialogStatus().subscribe(progressDialogTest)
        viewModel.getComicsFromServer()
        `when`(repository.getComics(anyInt())).thenReturn(single.delaySubscription(delayer))
        viewModel.getComicsFromServer()
        viewModel.loadMore(visibleItemCount, totalItemCount, firstVisibleItem)

        /* Then */
        Assert.assertThat(viewModel.getComics().size, `is`(2))
        progressDialogTest.assertValueCount(3)
        progressDialogTest.assertValueAt(0) {
            Assert.assertThat(it, `is`(true))
            true
        }
        progressDialogTest.assertValueAt(1) {
            Assert.assertThat(it, `is`(false))
            true
        }
        progressDialogTest.assertValueAt(2) {
            Assert.assertThat(it, `is`(true))
            true
        }
    }

    @Test
    fun `Given isLoading is false, nextPageFlag is false and total of visibleItemCount, firstVisibleItem and VISIBLE_THRESHOLD less than or similar totalItemCount - When call loadMore() - Then call getComicFromServer()`() {
        /* Given */
        val test = TestObserver<MutableList<Comic>>()
        val visibleItemCount = 10
        val firstVisibleItem = 10
        val totalItemCount = 100
        `when`(repository.getComics(anyInt())).thenReturn(Single.just(HomeResponse(true,
                mutableListOf(Comic(1, "jindo", "football comic", "author", 100, true, 100, "url1"),
                        Comic(2, "jindodinho", "football comic", "author", 50, true, 100, "url2")))))
        /* When */
        viewModel.getComicsObservable().subscribe(test)
        viewModel.getComicsFromServer()
        viewModel.loadMore(visibleItemCount, totalItemCount, firstVisibleItem)

        /* Then */
        test.assertValue {
            Assert.assertThat(it.size, `is`(2))
            Assert.assertThat(it[0].id, `is`(1))
            Assert.assertThat(it[0].name, `is`("jindo"))
            Assert.assertThat(it[1].id, `is`(2))
            Assert.assertThat(it[1].name, `is`("jindodinho"))
            true
        }
    }
}
