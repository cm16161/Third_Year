/*
 CS/ECE 552 Spring '19
 Homework #3, Problem 1

 4-1 mux template
 */
module mux4_1(InA, InB, InC, InD, S, Out);
   input        InA, InB, InC, InD;
   input [1:0]  S;
   output       Out;

   // YOUR CODE HERE
   wire         AB_OUT, CD_OUT, result;
   //assign S[0] = 2'b00
   mux2_1 mux0 (.InA(InA), .InB(InB), .S(S), .Out(AB_OUT));
   mux2_1 mux1 (.InA(InC), .InB(InD), .S(S), .Out(CD_OUT));
   mux2_1 mux3 (.InA(AB_OUT), .InB(CD_OUT), .S(S), .Out(result));
   assign Out = result;
   

endmodule
