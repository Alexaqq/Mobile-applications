package com.example.lab_3.data.repository

import com.example.lab_3.R
import com.example.lab_3.data.model.Place

object PlaceRepository {

    fun getCafes(): List<Place> = listOf(
        Place("cafe1", "Кофе Like", "Современное уютное кафе с авторским кофе и десертами.", R.drawable.cafe1),
        Place("cafe2", "Бодрый день", "Место для энергичного старта дня с ароматным кофе и круассанами.", R.drawable.cafe2),
        Place("cafe3", "Черная кошка", "Атмосферная кофейня с винтажным интерьером и живой музыкой.", R.drawable.cafe3),
        Place("cafe4", "Кофеварка", "Локальная кофейня с классическим эспрессо и уютной атмосферой.", R.drawable.cafe4),
        Place("cafe5", "Кофе и Книги", "Тихое место для чтения с отличным выбором напитков.", R.drawable.cafe5)
    )

    fun getParks(): List<Place> = listOf(
        Place("park1", "Центральный парк", "Главный парк города с аллеями, фонтанами и аттракционами.", R.drawable.park1),
        Place("park2", "Парк Кирова", "Огромная зеленая зона с набережной и лодочной станцией.", R.drawable.park2),
        Place("park3", "Парк 40-летия Победы", "Исторический парк с памятниками и тенистыми дорожками.", R.drawable.park3),
        Place("park4", "Парк у Дворца спорта", "Современный городской парк рядом с центром развлечений.", R.drawable.park4)
    )

    fun getRestaurants(): List<Place> = listOf(
        Place("rest1", "Тори", "Японская и азиатская кухня с уютным интерьером.", R.drawable.restaurant1),
        Place("rest2", "Печки-Лавочки", "Домашняя русская кухня в традиционном стиле.", R.drawable.restaurant2),
        Place("rest3", "ШашлыкоFF", "Мясной ресторан с грилем и мангалом.", R.drawable.restaurant3),
        Place("rest4", "Грильница", "Современное место с бургерами и стейками.", R.drawable.restaurant4)
    )

    fun getForKids(): List<Place> = listOf(
        Place("kids1", "Кидс Парк", "Игровая зона с горками и лабиринтами для детей всех возрастов.", R.drawable.kids1),
        Place("kids2", "Дино Парк", "Парк с динозаврами и аттракционами.", R.drawable.kids2),
        Place("kids3", "Развивайка", "Центр раннего развития с профессиональными педагогами.", R.drawable.kids3),
        Place("kids4", "ИгроМир", "Игровая площадка в помещении с аниматорами.", R.drawable.kids4)
    )

    fun getMalls(): List<Place> = listOf(
        Place("mall1", "ТЦ Столица", "Современный торговый центр с кинотеатром и фудкортом.", R.drawable.mall1),
        Place("mall2", "Петровский", "Много магазинов одежды, техники и ресторанов.", R.drawable.mall2),
        Place("mall3", "Талисман", "Торгово-развлекательный центр с семейным досугом.", R.drawable.mall3),
        Place("mall4", "Гвоздь", "Крупный торговый комплекс рядом с центром.", R.drawable.mall4)
    )

    fun getAllPlaces(): List<Place> {
        return getCafes() + getParks() + getRestaurants() + getForKids() + getMalls()
    }
}
