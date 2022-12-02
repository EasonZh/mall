function editCart(id,amount) {
    window.location.href = 'cart/editCart?id='+
    id + "&amount=" + amount;


}
// window.onload=function(){
//     var vue = new Vue({
//         el:"#cart_div",
//         data:{
//             cart:{}
//         },
//         methods:{
//             getCart:function(){
//                 axios({
//                     methods:"POST",
//                     url:"cart",
//                     params:{
//                         operate:'cartInfo'
//                     }
//                 }).then(function(value){
//                     var cart = value.data;
//                     vue.cart = cart;
//                 }).catch(function(reason){
//
//                 })
//             },
//             editCart:function(productId , amount){
//                 axios({
//                     method:"POST",
//                     url:"cart",
//                     params:{
//                         // operate:'editCart',
//                         productId:productId,
//                         amount:amount
//                     }
//                 }).then(function (value) {
//                         vue.getCart();
//                     })
//                     .catch(function (reason) {  });
//             }
//         },
//             mounted:function(){
//                 this.getCart();
//             }
//
//
//         });
// }