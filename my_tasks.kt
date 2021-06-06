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
    
    //task 3:
    var arr = listOf(1, -2, 3, 12, -5, 8, 4, -6, -3)
    println(getLargestSequence(arr)) //result: [3, 12, -5, 8, 4]
    
    //task 4:
    var arr2 = listOf(4, 6, 10, 24)
    //println(getGcdOfList(arr2)) // result: 2 
    println(getLcmOfList(arr2)) //result: 120
    

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

//task 3:
fun getLargestSequence(arr :List<Int>): List<Int>{
    var sum = arr.sum()
    var sequence = arr
    println(sum)
    println(sequence)

    for(startIdx in arr.indices){ //all starting elems
        for(numOfElems in 1 until (arr.size-startIdx)) {//(numTillEnd) - all possible sequence lengths
            val endIdx = startIdx + numOfElems
            val tempArr = arr.slice(startIdx..endIdx)

            val tempSum = tempArr.sum()
            if(tempSum >= sum){
                sum = tempSum
                sequence = tempArr
            }
            println("startIdx=$startIdx, endIdx=$endIdx, sum=$tempSum")
        }
    }
    println("largest sum = $sum")
    return sequence

}

//task 4:
fun getLcmOfList(arr:List<Int>):Int{ //Least Common Multiple
    var commonLCM :Int = arr[0]
    for(elem in arr){
        commonLCM = getLCM(commonLCM, elem)
    }
    return commonLCM
}

//Euclid's algorithm for finding the Greatest Common Denominator of two numbers:
fun getGCD(a:Int, b:Int):Int{
    var m = a
    var n = b
    while(m != n){
        if(m > n){
            m = m-n
        }else{
            n = n-m
        }
    }
    return m
}

fun getGcdOfList(arr:List<Int>): Int{
    var commonGCD :Int = arr[0]
    for(elem in arr){
        commonGCD = getGCD(commonGCD, elem)
    }
    return commonGCD
}

//Formula: LCM = ( a * b ) / GCD
fun getLCM(a :Int, b: Int, ):Int = (a * b)/getGCD(a, b)






/*
Output for task 3: 
12
[1, -2, 3, 12, -5, 8, 4, -6, -3]
startIdx=0, endIdx=1, sum=-1
startIdx=0, endIdx=2, sum=2
startIdx=0, endIdx=3, sum=14
startIdx=0, endIdx=4, sum=9
startIdx=0, endIdx=5, sum=17
startIdx=0, endIdx=6, sum=21
startIdx=0, endIdx=7, sum=15
startIdx=0, endIdx=8, sum=12
startIdx=1, endIdx=2, sum=1
startIdx=1, endIdx=3, sum=13
startIdx=1, endIdx=4, sum=8
startIdx=1, endIdx=5, sum=16
startIdx=1, endIdx=6, sum=20
startIdx=1, endIdx=7, sum=14
startIdx=1, endIdx=8, sum=11
startIdx=2, endIdx=3, sum=15
startIdx=2, endIdx=4, sum=10
startIdx=2, endIdx=5, sum=18
startIdx=2, endIdx=6, sum=22
startIdx=2, endIdx=7, sum=16
startIdx=2, endIdx=8, sum=13
startIdx=3, endIdx=4, sum=7
startIdx=3, endIdx=5, sum=15
startIdx=3, endIdx=6, sum=19
startIdx=3, endIdx=7, sum=13
startIdx=3, endIdx=8, sum=10
startIdx=4, endIdx=5, sum=3
startIdx=4, endIdx=6, sum=7
startIdx=4, endIdx=7, sum=1
startIdx=4, endIdx=8, sum=-2
startIdx=5, endIdx=6, sum=12
startIdx=5, endIdx=7, sum=6
startIdx=5, endIdx=8, sum=3
startIdx=6, endIdx=7, sum=-2
startIdx=6, endIdx=8, sum=-5
startIdx=7, endIdx=8, sum=-9

largest sum = 22
[3, 12, -5, 8, 4]
 */

