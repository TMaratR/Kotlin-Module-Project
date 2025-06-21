class Menu(private val title: String, private val items: List<MenuSection>) {
    fun show(){
        while (true){
            println("\n$title")
            items.forEachIndexed { index, item -> println("$index. ${item.title}") }

            print("Введите номер пункта меню: ")
            val answer = readLine()?.trim()
            if(answer.isNullOrEmpty()){
                println("Ошибка: поле не может быть пустым.")
                continue
            }
            val index = answer.toIntOrNull()
            if(index == null){
                println("Некорректный ввод. Необходимо ответить числом")
                continue
            }

            if (index in items.indices){
                items[index].action()
                break
            }else{
                println("Некорректный ввод: такого пункта не существует")
            }
        }
    }
}