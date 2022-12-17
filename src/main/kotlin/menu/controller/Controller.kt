package menu.controller

import menu.domain.Coach
import menu.domain.MenuRecommend
import menu.utils.RepeatInputProcess
import menu.view.InputView
import menu.view.OutputView

@Suppress("UNCHECKED_CAST")
class Controller {
    private val outputView = OutputView()
    private val inputView = InputView()
    private val menuRecommend = MenuRecommend()

    init {
        outputView.printStartMent()
    }

    fun operate() {
        val coaches = readCoaches()
        readCantEatMenu(coaches)
        val categories = menuRecommend.recommendCategory()
        coaches.forEach { coach ->
            menuRecommend.recommendMenusToCoach(categories, coach)
            println(coach.getRecommendedMenus())
        }
    }

    private fun readCoaches(): List<Coach> {
        val coaches = mutableListOf<Coach>()
        outputView.printInputCoachNamesMent()
        val names = RepeatInputProcess.repeat { inputView.readCoachNames() } as List<String>
        names.forEach {
            coaches.add(Coach(it))
        }
        return coaches
    }

    private fun readCantEatMenu(coaches: List<Coach>) {
        coaches.forEach {
            outputView.printInputCantEatMenuMent(it.name)
            it.setCantEatMenus(RepeatInputProcess.repeat { inputView.readCantEatMenus() } as List<String>)
        }
    }
}