import java.util.function.Predicate


fun main(){
    //task 1:
    var list = listOf("A2", "K1", "S34", "A11", "A1")
    println(sortList(list)) //result: [A1, A2, A11, K1, S34]

    //task 2:
    var list2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val predicate1 : (Int) -> Boolean = { a:Int -> a>3 }
    val predicate2 : (Int) -> Boolean = { a:Int -> a%2 == 0 }

    println(getListByConditions(list2, predicate1, predicate2)) //result: [4, 6, 8, 10]

}

fun sortList(list : List<String>): List<String>{

    val myComparator = Comparator { str1: String, str2: String ->
        if(str1.first() == str2.first()) { //if fist letters are the same, compare numbers:
            str1.substring(1).toInt() - str2.substring(1).toInt()
        }else{ //just compare letters:
            str1.first() - str2.first()
        }
    }
    return list.sortedWith(myComparator)

}

//task 2:
fun <R> getListByConditions(list:List<R>, vararg conditions:Predicate<R>):MutableList<R>{

    var resultArr = ArrayList<R>()
    var totalCondition = conditions[0]
    for(cond in conditions){
        totalCondition=totalCondition.and(cond)
    }
    for(elem in list){
        if(totalCondition.test(elem)){
            resultArr.add(elem)
        }
    }
    return resultArr
}
