import java.awt.Color
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Writer
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashMap

class GrafConfiguration(private val path: String) {

    private var hash : HashMap<String, String>;
    private lateinit var writer: BufferedWriter;
    private val reader : ReadInformation;

    init {
        var file : File = File(path);
        if(!(file.exists())) {
            file.createNewFile();
            println("Created new file even though it didn't exist yet. This was done to prevent a disaster of exceptions and of reasons of safety!");
        }
        hash = readMap();
        reader = ReadInformation(hash);
    }

    private fun readMap(): HashMap<String, String> {
        hash = HashMap<String, String>()
        var strings: List<String>;
        try {
            strings = Files.readAllLines(Paths.get(path));
        } catch (e: NoSuchFileException) {
            e.printStackTrace();
            strings = emptyList();
        }
        if (strings.isEmpty()) return hash;

        for (str in strings) {
            try {
                hash[str.split(" : ")[0]] = str.split(" : ")[1];
            } catch (e: ArrayIndexOutOfBoundsException) {
                e.printStackTrace();
                continue;
            }

        }
        return hash;
    }

    public fun getUUID(key: String): UUID? = reader.getUUID(key);

    public fun getString(key: String): String? = reader.getString(key);

    public fun getSet(key : String): Set<Any>? = reader.getSet(key);

    public fun getList(key : String): List<Any>? = reader.getList(key);

    public fun getArray(key : String): Array<Any>? = reader.getArray(key);

    public fun getInt(key: String): Int? = reader.getInt(key);

    public fun getBoolean(key : String): Boolean? = reader.getBoolean(key);


    public fun getDouble(key: String): Double? = reader.getDouble(key);


    public fun getFloat(key: String): Float? = reader.getFloat(key);


    public fun getDate(key: String): LocalDate? = reader.getDate(key);


    public fun set(key: String, input: Any) {

        val saveInfo = SaveInformation();
        when (input) {

            is Map<*, *> -> {
                println("${Color.RED} Bitte benutze stattdessen setMap()")
                return;
            }
            is Collection<*> -> {
                saveInfo.setCollections(key, input as Collection<Any>, hash);
                return;
            }
            else -> {
                saveInfo.set(key, input.toString(), hash);
                return;
            }

        }

    }

    public fun isSet(key: String): Boolean = hash.containsKey(key);

    public fun setMap(map: HashMap<Any, Any>) {
        val saveInfo = SaveInformation();
        saveInfo.setMap(map, hash);
    }

    public fun save(saveFile: File = File(path)) {
        writer = BufferedWriter(FileWriter(saveFile));
        for (key in hash.keys) {
            writer.write(key + " : " + hash.get(key));
            writer.newLine();

        }
        writer.flush();
        writer.close();
    }
}




