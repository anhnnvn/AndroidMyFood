package com.ftech.dev.android_my_food.ui.cart

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ftech.dev.android_my_food.data.repository.ItemInCartRepository
import com.ftech.dev.android_my_food.data.repository.OrderRepository
import com.ftech.dev.android_my_food.data.source.local.ItemInCartEntity
import com.ftech.dev.android_my_food.data.source.local.OrderEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CartViewModel : ViewModel() {

    private val itemInCartRepository = ItemInCartRepository()
    private val orderRepository = OrderRepository()

    val liveItemInCart = MutableLiveData<ItemInCartEntity>()
    val liveOrders = orderRepository.getLiveDataOrders()
    val listItemInCartLiveData = itemInCartRepository.getAllItemInCartLiveData()

    var amount = MutableLiveData<Int>(0)
    var payment = MutableLiveData<String>("Tiền mặt")
    var isOrdering = MutableLiveData<Boolean>(true)

    var total = MutableLiveData<Int>(0)

    fun upAmount() {
        amount.value = liveItemInCart.value?.amount?.let { amount.value?.plus(it) }
        total.value = liveItemInCart.value?.total?.let { total.value?.plus(it) }
    }

    fun downAmount() {
        if (amount.value!! > 0) {
            amount.value = liveItemInCart.value?.amount?.let { amount.value?.minus(it) }
            total.value = liveItemInCart.value?.total?.let { total.value?.minus(it) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkOut() : Boolean{
        val currentDateTime = LocalDateTime.now()
        val time = currentDateTime.format(
            DateTimeFormatter.ISO_DATE)
        if (amount.value!=0) {
            val orderEntity = OrderEntity(
                name = "Order", total = total.value!!, paymentMethod = payment.value!!,
                amount = amount.value!!, date = time, status = true
            )

            insertOrder(orderEntity)

            amount.value = 0
            total.value = 0
            itemInCartRepository.deleteAll()
            isOrdering.value = false
            return true
        }
        return false
    }

    fun insert(itemInCartEntity: ItemInCartEntity) {
        itemInCartRepository.insert(itemInCartEntity)
    }

    fun delete(itemInCartEntity: ItemInCartEntity) {
        itemInCartRepository.delete(itemInCartEntity)
    }

    fun insertOrder(orderEntity: OrderEntity){
        orderRepository.insert(orderEntity)
    }

    fun deleteAll(){
        itemInCartRepository.deleteAll()
    }

    fun isAmountValid() : Boolean{
        return (amount.value!! > 0)
    }

}