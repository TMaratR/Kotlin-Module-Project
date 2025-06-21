class Сontroler {
    private val archives = mutableListOf<Archive>()

    fun start() {
        var isEnd = true
        while (isEnd) {
            val menuSections = mutableListOf<MenuSection>()

            menuSections.add(MenuSection("Создать архив") { createArchive() })

            archives.forEach { archive ->
                menuSections.add(MenuSection("Открыть архив: ${archive.name}") {
                    viewArchive(archive)
                })
            }
            menuSections.add(MenuSection("Выход") { isEnd = false })
            Menu("Список архивов: ", menuSections).show()
        }
    }

    private fun createArchive(){
        val name = nonEmptyText("Введите имя архива: ")
        archives.add(Archive(name))
        println("Архив \"$name\" создан.")
    }

    private fun viewArchive(archive: Archive){
        val menuSections = mutableListOf(
            MenuSection("Создать заметку"){ createNote(archive)}
        )

        archive.notes.forEach{note ->
            menuSections.add(MenuSection(note.title){viewNote(note, archive)})
        }

        menuSections.add(MenuSection("Назад"){ })

        Menu("Заметки архива \"${archive.name}\":", menuSections).show()
    }

    private fun createNote(archive: Archive){
        val title = nonEmptyText("Введите название заметки: ")
        val content = nonEmptyText("Введите текст заметки: ")

        archive.notes.add(Note(title, content))
        println("Заметка \"$title\" добавлена")
        viewArchive(archive)
    }

    private fun nonEmptyText(title : String) : String{
        while (true){
            println(title)
            val text = readLine()?.trim()
            if (!text.isNullOrEmpty())
                return text
            println("Поле не может быть пустым.")
        }
    }

    private fun viewNote(note: Note, archive: Archive){
        println("\nНазвание: ${note.title}")
        println(note.content)
        println("Нажмите любую кнопку для возврата")
        readLine()
        viewArchive((archive))
    }
}