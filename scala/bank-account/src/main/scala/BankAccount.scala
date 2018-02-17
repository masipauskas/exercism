import java.util.concurrent.atomic.{AtomicInteger, AtomicLong}

trait BankAccount {

  def closeAccount(): Unit

  def getBalance: Option[Int]

  def incrementBalance(increment: Int): Option[Int]
}

class ConcurrentBankAccount extends BankAccount {

  @volatile private var isClosed = false
  private val balance: AtomicInteger = new AtomicInteger(0)

  override def closeAccount(): Unit = isClosed = true


  override def getBalance: Option[Int] = {
    if (isClosed) None
    else Some(balance.get())
  }

  override def incrementBalance(increment: Int): Option[Int] = {
    if (isClosed) None
    else Some(balance.addAndGet(increment))
  }

}

object Bank {
  def openAccount(): BankAccount = new ConcurrentBankAccount
}

