package books.funreactdomainmodels.ch1

object AccountClient3 {
  import Customer._
  import AccountEntities._
  import AccountService._
  import java.util.Date
  import java.util.Calendar

  val custStub = getStubCustomer()                //> custStub  : books.funreactdomainmodels.ch1.Customer = Customer(1,Joe Stub,Ad
                                                  //| dress())
  val acct1 = verifyCustomer(custStub).map { c =>
    openCheckingAccount(c, Calendar.getInstance().getTime())
  }.getOrElse(throw new Exception("Verification failed for customer"))
                                                  //> acct1  : books.funreactdomainmodels.ch1.AccountEntities.CheckingAccount = Ch
                                                  //| eckingAccount(STUB_ID,Joe Stub,Bank(),Address(),Sat Oct 24 12:33:07 PDT 2015
                                                  //| ,None,Balance(0.0))
  acct1.balance == Balance(0.0)                   //> res0: Boolean = true
  
  val acct1_1 = creditChecking(acct1, 1000.00)    //> acct1_1  : scala.util.Try[books.funreactdomainmodels.ch1.AccountEntities.Che
                                                  //| ckingAccount] = Success(CheckingAccount(STUB_ID,Joe Stub,Bank(),Address(),Sa
                                                  //| t Oct 24 12:33:07 PDT 2015,None,Balance(1000.0)))
  acct1                                           //> res1: books.funreactdomainmodels.ch1.AccountEntities.CheckingAccount = Check
                                                  //| ingAccount(STUB_ID,Joe Stub,Bank(),Address(),Sat Oct 24 12:33:07 PDT 2015,No
                                                  //| ne,Balance(0.0))
  
  for {
    a <- creditChecking(acct1, 1000)
    b <- debitChecking(a, 200)
    c <- debitChecking(b, 190)
  } yield c                                       //> res2: scala.util.Try[books.funreactdomainmodels.ch1.AccountEntities.Checking
                                                  //| Account] = Success(CheckingAccount(STUB_ID,Joe Stub,Bank(),Address(),Sat Oct
                                                  //|  24 12:33:07 PDT 2015,None,Balance(610.0)))
 
  '''                                             //> res3: Char('\'') = '
}