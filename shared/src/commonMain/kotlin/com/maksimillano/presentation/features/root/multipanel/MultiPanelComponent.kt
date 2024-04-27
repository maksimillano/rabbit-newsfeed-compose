package com.maksimillano.presentation.features.root.multipanel

import androidx.compose.runtime.Composable
import com.maksimillano.presentation.base.BaseComponent
import com.maksimillano.presentation.base.ContextWrapper

class MultiPanelComponent(
    contextWrapper: ContextWrapper
) : BaseComponent(contextWrapper) {
    // Children
//    private val simpleNavigation = SimpleNavigation<(NavigationState) -> NavigationState>()
//    private val navState = BehaviorSubject<NavigationState?>(null)
//
//    val children: Value<Children> = children(
//            source = simpleNavigation,
//            key = "children",
//            initialState = MultiPanelComponent::NavigationState,
//            navTransformer = { navState, event -> event(navState) },
//            stateMapper = { navState, children ->
//                Children(
//                    isMultiPane = navState.isMultiPane,
//                    listChild = children.first() as Child.Created<*, Renderable>,
//                    detailsChild = if (navState.isMultiPane) {
//                        children.last() as Child.Created<*, Renderable>?
//                    } else {
//                       null
//                    },
//                )
//            },
//            onStateChanged = { newState, _ -> navState.onNext(newState) },
//            childFactory = ::child,
//        )
//
    @Composable
    override fun render() {
    }
//
//    private fun child(config: FeatureConfig, componentContext: ComponentContext): Renderable =
//        when (config) {
//            is FeatureConfig.List -> listComponent(componentContext)
//            is FeatureConfig.Details -> detailsComponent(config, componentContext)
//            else -> throw IllegalStateException()
//        }
//
//    private fun listComponent(componentContext: ComponentContext): ArticleListComponent =
//        DefaultArticleListComponent(
//            componentContext = componentContext,
//            database = database,
//            selectedArticleId = navState.notNull().map { if (it.isMultiPane) it.articleId else null },
//            onArticleSelected = { id -> simpleNavigation.navigate { it.copy(articleId = id) } },
//        )
//
//    private fun detailsComponent(config: Config.Details, componentContext: ComponentContext): ArticleDetailsComponent =
//        DefaultArticleDetailsComponent(
//            componentContext = componentContext,
//            database = database,
//            articleId = config.articleId,
//            isToolbarVisible = navState.notNull().map { !it.isMultiPane },
//            onFinished = { simpleNavigation.navigate { it.copy(articleId = null) } },
//        )
//
//    override fun setMultiPane(isMultiPane: Boolean) {
//        simpleNavigation.navigate { it.copy(isMultiPane = isMultiPane) }
//    }
//
//    @Parcelize
//    private data class NavigationState(
//        val isMultiPane: Boolean = false,
//        val articleId: Long? = null,
//    ) : NavState<FeatureConfig>, Parcelable {
//        override val children: List<ChildNavState<FeatureConfig>>
//            get() =
//                listOfNotNull(
//                    SimpleChildNavState(Config.List, if (isMultiPane || (articleId == null)) ChildNavState.Status.ACTIVE else ChildNavState.Status.INACTIVE),
//                    if (articleId != null) SimpleChildNavState(Config.Details(articleId), ChildNavState.Status.ACTIVE) else null,
//                )
//    }
//
//    data class Children(
//        val isMultiPane: Boolean,
//        val listChild: Child.Created<*, Renderable>,
//        val detailsChild: Child.Created<*, Renderable>?,
//    )
}