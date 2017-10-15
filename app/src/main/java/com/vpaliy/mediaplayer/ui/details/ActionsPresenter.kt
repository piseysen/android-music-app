package com.vpaliy.mediaplayer.ui.details

import com.vpaliy.mediaplayer.domain.interactor.ClearInteractor
import com.vpaliy.mediaplayer.domain.interactor.InsertInteractor
import com.vpaliy.mediaplayer.domain.model.Track
import com.vpaliy.mediaplayer.ui.details.ActionsContract.View
import com.vpaliy.mediaplayer.ui.details.ActionsContract.Presenter
import javax.inject.Inject
import com.vpaliy.mediaplayer.di.scope.ViewScope

@ViewScope
class ActionsPresenter @Inject
constructor(val liker:InsertInteractor<Track>,
            val adder:InsertInteractor<Track>,
            val disliker:ClearInteractor<Track>,
            val remover:ClearInteractor<Track>): Presenter{

    private lateinit var view:View

    override fun add(track: Track)=
            adder.insert(view::added,this::error,track)
    override fun remove(track: Track)=
            remover.remove(view::removed,this::error,track)
    override fun dislike(track: Track) =
            disliker.remove(view::disliked,this::error,track)
    override fun like(track: Track) =
            liker.insert(view::liked,this::error,track)

    override fun stop(){}

    private fun error(ex:Throwable){
        view.error()
        ex.printStackTrace()
    }

    override fun attach(view: View) {
        this.view=view
    }
}