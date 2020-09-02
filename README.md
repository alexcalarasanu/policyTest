# QMetric Java Exercise

Our Java coding exercise is inspired by 
[PragDave Supermaket Kata](http://codekata.com/kata/kata01-supermarket-pricing/). 
 
> [...] to experiment with various models for representing money and prices that are flexible enough to deal with 
>[a variety of] pricing schemes, and at the same time are generally usable (at the checkout, for stock management, 
>order entry, and so on). 

## The task

This project provides the code for an initial model of a supermarket. 
The supermarket's inventory consists of _products_ that can be sold _by unit_ (e.g. a can of baked beans) or _by weight_
(e.g. loose carrots).
The total price of a shopping basket has currently been calculated as a simple sum of its items. 
  
The supermarket would like to offer _pricing discount_ schemes, for example:
- Buy one, get one free
- Buy two items for £1
- Buy three items for the price of two
- Buy one kilo of vegetables for half price

Some discounts would be applicable for single products (e.g. a can of baked beans), while others could apply across a 
group of products (e.g. vegetables).

Your task is to extend the functionality of Basket to implement _a component_ that can calculate the __total amount__ 
to pay for the items in a basket, __including any discounts that would be applicable__ for the current state of the 
basket.

* You should think about how the relationships between Basket, Item, and Product will need to be refactored to achieve 
this
* You should consider how your solution could be extended to provide more complicated discount schemes

First and foremost this is an opportunity for you to demonstrate your _software design_ skills within a small problem 
space. 

Please read through the description carefully and implement a solution that includes __at least one__ of the pricing 
discount schemes described.

We would like you spend __no more than one hour__ on this task. 
 
### We are looking for...

- Clean, tidy, tested, and __working__ code (with accurate pricing calculations) 
- Use of appropriate design patterns and [SOLID](https://en.wikipedia.org/wiki/SOLID) principles
- Small incremental changes with good comments, as demonstrated in your commit history

**Above all, we would rather see a small codebase with fewer features than failure to observe the points above.**

You may add any libraries you feel are appropriate.
But we don't expect to see databases or other persistence layers, dependency injection frameworks, web servers, REST 
APIs, or web frameworks;
we are **not** looking for a fully functioning supermarket system!

### Tips

- Plan your time before you start coding. Start small and add features incrementally
- Focus on the pricing model and the behaviour of your components -- you only need to demonstrate that your design 
works, rather than an exhaustive model of numerous supermarket items 
- If you run out of time, describe your intentions in the [NOTES.md](NOTES.md) file

We really don't want you to over-engineer the solution -- and you really don't have time for that! -- but be prepared 
to extend the functionality in the next step of the interview process. 

## Submission

Clone or fork this project into a _publicly accessible_ git repository of your own, and email the URL to to 
[tpadberg@qmetric.co.uk](mailto:tpadberg@qmetric.co.uk). 

Good Luck!
