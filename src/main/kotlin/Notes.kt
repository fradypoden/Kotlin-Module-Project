import java.util.*

class Notes {
    private val archives = mutableListOf<Archive>()

    private fun menu(name: String, create: String, look: String, action: Map<String, () -> Unit>) {
        while (true) {
            println("$name\n$create\n$look\n0. Выход")
            when (val scanner = Scanner(System.`in`).nextLine()) {
                "1" -> action[scanner]?.invoke()
                "2" -> action[scanner]?.invoke()
                "0" -> return
                else -> println("Неверный выбор, попробуйте снова.")
            }
        }
    }

    fun viewArchiveMenu() {
        menu(
            "Главный экран: Список архивов", "1. Создать архив", "2. Посмотреть архивы", mapOf(
                "1" to { createArchive() },
                "2" to { viewArchives() }
            ))
    }

    private fun viewNotesMenu(archive: Archive) {
        menu(
            "Архив: ${archive.name}", "1. Создать заметку", "2. Посмотреть заметку", mapOf(
                "1" to { addNoteToArchive(archive) },
                "2" to { viewNotes(archive) }
            ))
    }

    private fun createArchive() {
        println("Введите название архива")
        val archive = emptyCheck()
        archives.add(Archive(archive))
        println("Архив '${archive}' создан.")
    }

    private fun <T> viewItems(items: List<T>, title: String, selectionMessage: String, action: (T) -> Unit) {
        if (items.isEmpty()) {
            println("Нет $title.")
            return
        }

        items.forEachIndexed { index, item ->
            println("${index + 1}. $item")
        }

        println(selectionMessage)
        val index = errorCheck(0, items.size)
        if (index > 0) {
            action(items[index - 1])
        }
    }

    private fun viewArchives() {
        viewItems(archives, "архивов", "Выберите архив для просмотра заметок или 0 для возврата:", ::viewNotesMenu)
    }

    private fun viewNotes(archive: Archive) {
        val notes = archive.getNotes()
        viewItems(notes, "заметок в архиве '${archive.name}'", "Выберите заметку для просмотра или 0 для возврата:", ::viewNote)
    }

    private fun addNoteToArchive(archive: Archive) {
        println("Введите название заметки:")
        val name = emptyCheck()

        println("Введите текст заметки:")
        val text = emptyCheck()

        archive.addNote(Note(name, text))
        println("Заметка '$name' добавлена в архив '${archive.name}'.")
    }

    private fun viewNote(note: Note) {
        println("название заметки: ${note.name}")
        println("текст заметки: ${note.text}")
    }
}
