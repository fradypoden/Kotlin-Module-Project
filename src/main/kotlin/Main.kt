import java.util.Scanner

fun main() {
    val q = Notes()
    q.viewArchiveMenu()
}

class Note(
    val name: String,
    val text: String
) {
    override fun toString(): String {
        return name
    }
}

class Archive(val name: String) {
    private val notes = mutableListOf<Note>()

    override fun toString(): String {
        return name
    }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun getNotes(): MutableList<Note> {
        return notes
    }
}

fun emptyCheck(): String {
    var scanner: String?
    do {
        scanner = Scanner(System.`in`).nextLine()
        if (scanner.isNullOrBlank()) {
            println("Название не должно быть пустым. Попробуйте еще раз")
        }
    } while (scanner.isNullOrBlank())
    return scanner
}

fun errorCheck(min: Int, max: Int): Int {
    val scanner = Scanner(System.`in`)
    var input: String
    while (true) {
        input = scanner.nextLine()
        if (input.isNullOrBlank() || !input.all { it.isDigit() }) {
            println("Ошибка! Введите число")
        } else {
            val index = input.toInt()
            if (index in min..max) {
                return index
            } else {
                println("Ошибка! Введите число в диапазоне от $min до $max")
            }
        }
    }
}
