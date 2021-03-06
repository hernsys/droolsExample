[condition][Customer]There is a customer=$customer:Customer()
[condition][Customer]-The expense of the customer is greater than {expense}=eval($customer.getExpense() > {expense})
[condition][Customer]-The status of the customer is registered=eval($customer.isRegistered())
[condition][Customer]-The status of the customer is not registered=eval($customer.isRegistered()==false)
[consequence][]Calculate a {percentage} percent discount=$customer.setDiscount(getDiscount($customer.getExpense(), {percentage}));
