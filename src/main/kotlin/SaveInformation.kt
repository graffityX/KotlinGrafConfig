class SaveInformation {


    public fun set(key: String, o: String, hash: HashMap<String, String>) = hash.put(key, o.toString());


    public fun setCollections(key: String, list: Collection<Any>, hash: HashMap<String, String>) {
        var str: String = "";
        for (o in list) {
            str = "$str$o,";
        }
        hash[key] = str;

    }


    public fun setMap(map: HashMap<Any, Any>, hash: HashMap<String, String>) =
        map.keys.forEach { x -> hash[x.toString()] = map[x].toString() };


}

