package br.edu.ifce.lds.coapp.plans.di

import br.edu.ifce.lds.coapp.plans.dhandler.PlanDetailDataHandler
import br.edu.ifce.lds.coapp.plans.dhandler.PlanDetailDataHandlerImpl
import br.edu.ifce.lds.coapp.plans.presenter.PlanDetailPresenter
import br.edu.ifce.lds.coapp.plans.presenter.PlanDetailPresenterImpl
import br.edu.ifce.lds.coapp.plans.views.PlanDetailView
import br.edu.ifce.lds.coapp.utils.PlanScope
import dagger.Module
import dagger.Provides

/**
 * Created by lds on 7/18/17.
 */
@Module class PlanDetailActivityModule(val mView: PlanDetailView) {

    @Provides
    @PlanScope
    fun provideView(): PlanDetailView = mView

    @Provides
    @PlanScope
    fun providePresenter(mPresenter: PlanDetailPresenterImpl): PlanDetailPresenter = mPresenter

    @Provides
    @PlanScope
    fun provideDataHandler(mDataHandler: PlanDetailDataHandlerImpl): PlanDetailDataHandler = mDataHandler
}