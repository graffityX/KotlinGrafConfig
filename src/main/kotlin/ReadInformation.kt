import java.awt.Color
import java.lang.Exception
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashMap

class ReadInformation(private val hash: HashMap<String, String>) {

    public fun getInt(key: String): Int? {

        val toReturn = hash[key]?.toIntOrNull();
        if (toReturn == null) convertError("Int");
        return toReturn;
    }

    public fun getDouble(key: String): Double? {
        val toReturn = hash[key]?.toDoubleOrNull();
        if (toReturn == null) convertError("Double");
        return toReturn;
    }

    public fun getString(key: String): String? {
        val toReturn = hash[key];
        if (toReturn == null) convertError("Double");
        return toReturn;
    }

    public fun getFloat(key: String): Float? {
        val toReturn = hash[key]?.toFloatOrNull();
        if (toReturn == null) convertError("Float");
        return toReturn;
    }


    public fun getUUID(key: String): UUID? {
        try {
            return UUID.fromString(hash[key]);
        } catch (e: Exception) {
            convertError("UUID");
        }
        return null;
    }

    public fun getDate(key: String): LocalDate? {
        try {
            return LocalDate.parse(key);
        } catch (e: Exception) {
            convertError("LocalDate");
        }
        return null;
    }

    public fun getBoolean(key: String): Boolean? {
        try {
            return hash[key]?.toBooleanStrictOrNull();
        } catch (e: Exception) {
            convertError("Boolean");
        }
        return null;
    }


    public fun getList(key: String): List<String>? {
        try {
            return hash[key]?.split(",");
        } catch (e: Exception) {
            convertError("List");
        }
        return null;
    }

    public fun getArray(key: String): Array<Any>? {
        try {
            return hash[key]?.split(",")?.stream()?.toArray {
                arrayOfNulls<String>(it);
            }
        } catch (e: Exception) {
            convertError("List");
        }
        return null;
    }

    public fun getSet(key: String): Set<Any>? {
        try {
            return hash[key]?.split(",")?.toSet();
        } catch (e: Exception) {
            convertError("List");
        }
        return null;
    }

    private fun convertError(datatype: String) =
        println("${Color.RED} [GrafConfiguration] there has been an error converting the given String to $datatype or the invoked value doesn't exist!");
}