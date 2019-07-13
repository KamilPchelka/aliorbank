package dev.kamilpchelka.techtrial.aliorbank

import dev.kamilpchelka.techtrial.aliorbank.model.User
import dev.kamilpchelka.techtrial.aliorbank.repository.UserRepository
import dev.kamilpchelka.techtrial.aliorbank.service.UserServiceImpl
import spock.lang.Shared
import spock.lang.Specification

class UserServiceTests extends Specification{

    User user
    UserServiceImpl userService
    @Shared BigDecimal bigDecimalZero = BigDecimal.ZERO
    @Shared BigDecimal bigDecimalOne = BigDecimal.ONE

    def setup() {
        user = new User()
        user.setBalance(bigDecimalZero)

        Optional<User> optionalUser = Optional.of(user)
        UserRepository userRepository = Stub(UserRepository.class)
        userRepository.findById(1L) >> optionalUser

        userService = new UserServiceImpl(userRepository)
    }

    def "'updateBalance should return 'false' when repository is not able to find entity"() {
        given: "Optional of user that is null"
            Optional<User> optionalUser = Optional.empty()
        and: "Repository always return null for user with given id"
            UserRepository userRepository = Stub(UserRepository.class)
            userRepository.findById(1L) >> optionalUser
            UserServiceImpl userService = new UserServiceImpl(userRepository)
        when: "We ask for user that does not exist in database"
            boolean result = userService.updateBalance(1L, bigDecimalZero, "decrease")
        then: "We get that user's balance was not updated"
            !result

    }

    def "updateBalance should not update user's balance when we pass wrong balance operation"() {
        given: "Wrong balance operation type"
            String balanceOperation = "someType"
        when: "We try to perform such operation"
            boolean result = userService.updateBalance(1L, bigDecimalOne, balanceOperation)
        then: "We check that user's balance was not updated"
            bigDecimalZero.compareTo(user.getBalance()) == 0 && !result


    }
    def "updateBalance should increase user's balance when we pass 'increase' operation"() {
        given: "Increase balance operation type"
            String balanceOperation = "increase"
        when: "We try to perform increase operation"
            boolean result = userService.updateBalance(1L, bigDecimalOne, balanceOperation)
        then: "We check that user's balance is increased "
            user.getBalance().compareTo(bigDecimalOne) == 0 && result


    }

    def "updateBalance should decrease user's balance when we pass 'decrease' operation"() {
        given: "Increase balance operation type"
        String balanceOperation = "decrease"
        and: "We set user's balance to value of one big decimal"
            user.setBalance(bigDecimalOne)
        when: "We try to perform decrease operation"
        boolean result = userService.updateBalance(1L, bigDecimalOne, balanceOperation)
        then: "We check that user's balance is decreased "
        user.getBalance().compareTo(bigDecimalZero) == 0 && result


    }

}
