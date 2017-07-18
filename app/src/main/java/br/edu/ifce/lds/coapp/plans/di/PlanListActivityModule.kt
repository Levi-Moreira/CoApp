package br.edu.ifce.lds.coapp.plans.di

import br.edu.ifce.lds.coapp.plans.dhandler.PlanListDataHandler
import br.edu.ifce.lds.coapp.plans.dhandler.PlanListDataHandlerImpl
import br.edu.ifce.lds.coapp.plans.presenter.PlanListPresenter
import br.edu.ifce.lds.coapp.plans.presenter.PlanListPresenterImpl
import br.edu.ifce.lds.coapp.plans.views.PlanListView
import br.edu.ifce.lds.coapp.utils.PlanScope
import dagger.Module
import dagger.Provides

/**
 * Created by lds on 7/18/17.
 */
@Module class PlanListActivityModule(val mView: PlanListView) {

    @Provides
    @PlanScope
    fun provideView(): PlanListView = mView

    @Provides
    @PlanScope
    fun providePresenter(mPresenter: PlanListPresenterImpl): PlanListPresenter = mPresenter

    @Provides
    @PlanScope
    fun provideDataHandler(mDataHandler: PlanListDataHandlerImpl): PlanListDataHandler = mDataHandler
}