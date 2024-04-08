package generic

class Cage {
    private val animals : MutableList<Animal> = mutableListOf()

    fun getFirst() : Animal {
        return animals.first()
    }

    fun put(animal : Animal){
        this.animals.add(animal)
    }

    fun moveFrom(cage : Cage){
        this.animals.addAll(cage.animals)
    }
}

// 제네릭으로 변경
// 제네릭 문자는 대문자 1개가 정석
// 무공변 = 상속받은 클래스일지라도 변하지 않는다
class Cage2<T> {
    private val animals : MutableList<T> = mutableListOf()

    fun getFirst() : T{
        return animals.first()
    }

    fun printAll() {
        animals.forEach {
            print(it.toString() + " ")
        }
    }

    fun put(animal : T){
        this.animals.add(animal)
    }

    fun moveFrom(otherCage : Cage2<out T>){
        this.animals.addAll(otherCage.animals)
    }

    fun moveTo(otherCage : Cage2<in T>){
        otherCage.animals.addAll(this.animals)
    }
}


abstract class Animal(
    val name : String
)

abstract class Fish(name : String) : Animal(name)

class GoldFish(name : String) : Fish(name){
    override fun toString(): String {
        return "GoldFish"
    }
}
class Carp(name : String) : Fish(name){
    override fun toString(): String {
        return "Carp"
    }
}


fun main(args: Array<String>) {
    val cage = Cage()
    val carpCage = Cage2<Carp>()
    val goldFishCage = Cage2<GoldFish>()
    val fishCage = Cage2<Fish>()

    // 해당 방법이면 컴파일 오류 발생 -> return object가 다름
    // cage.put(Carp("붕어1"))
    // val carp : Carp = cage.getFirst()

    // 형변환을 하면 컴파일은 해결이지만 런타임이 발생
    // cage.put(GoldFish("금붕어1"))
    // val carp : Carp = cage.getFirst() as Carp

    // 예외처리는 됐지만 예외를 발생시키고 싶지 않음
    // val carp : Carp = cage.getFirst() as Carp? ?: throw IllegalArgumentException()

    // 제네릭 적용
    carpCage.put(Carp("붕어2"))
    goldFishCage.put(GoldFish("금붕어2"))


    // fishCage는 CarpCage를 못넣지만 나는 넣고싶다!
    // 이럴때 out 키워드를 넣는다
    fishCage.moveFrom(carpCage)
    fishCage.moveFrom(goldFishCage)

    fishCage.printAll()

    // goldFishCage안에 Fish이므로 안됨
    goldFishCage.put(GoldFish("금붕어"))
    // 하지만 나는 godlFish안에 Fish를 넣고싶다!
    goldFishCage.moveTo(fishCage)
}
