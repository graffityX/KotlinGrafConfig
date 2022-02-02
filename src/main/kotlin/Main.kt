fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: 2https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val cfg = GrafConfiguration("graf.graf");


    val list = cfg.getList("list");

    if (list != null) {
        list.forEach { x -> println(x) }
    }

    cfg.save()

}