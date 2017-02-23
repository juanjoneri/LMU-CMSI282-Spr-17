<p>Sumo wrestler Keiryo needs to put on weight in a hurry. With money in hand, he heads to a specialty food store where he is confronted with many items, each of which has a cost (in dollars) and a guarantee of the weight (in pounds) that he'll gain if he eats that item. His dilemma is which items to buy so as to maximize his total weight gain. Your Java program,

<i>SumoSolver</i>, will help him solve arbitrary instances of this problem via Dynamic Programming.</p>

<ul>

<li><p>The command-line args will be a sequence of cost-weight pairs, followed by the maximum amount he can spend.
For example, <i>java SumoSolver 48 51 49 52 55 99 100</i> describes an instance in which there are three items: the first item
costs <i>$48</i> and guarantees a weight gain of <i>51</i> pounds; the second item costs <i>$49</i> and guarantees a gain
of <i>52</i> pounds; the third item costs <i>$55</i> and guarantees a gain of <i>99</i> pounds; and Keiryo can spend at most <i>$100</i>.</p></li>

<li><p>All args should be positive integers, but, of course, your program should check their validity.</p></li>

<li><p>The output <b>must</b> look like this:</li></p>
<ul>
<p>$48 / 51 pounds<p>
<p>$49 / 52 pounds<p>
<p>2 items / $97 / 103 pounds<p>
</ul>

</ul>
