import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    val cage = Cage()

    // 해당 방법이면 컴파일 오류 발생 -> return object가 다름
    // cage.put(Carp("붕어1"))
    // val carp : Carp = cage.getFirst()

    // 형변환을 하면 컴파일은 해결이지만 런타임이 발생
    // cage.put(GoldFish("금붕어1"))
    // val carp : Carp = cage.getFirst() as Carp

    // 예외처리는 됐지만 예외를 발생시키고 싶지 않음
    // val carp : Carp = cage.getFirst() as Carp? ?: throw IllegalArgumentException()


}