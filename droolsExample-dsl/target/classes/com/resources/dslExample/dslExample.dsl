[condition][Customer]There is a customer with expense greater than {expense}=$customer : Customer( expense > new Double({expense}) );
[condition][Customer]There is a customer registered=$customer : Customer( registered == true )
[consequence][]calculate a {percentage} percent discount=$customer.setDiscount(getDiscount($customer.getExpense(), {percentage}));

