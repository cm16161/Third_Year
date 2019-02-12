/*
 CS/ECE 552 Spring '19
 Homework #3, Problem 1

 2-1 mux template
 */
module mux2_1(InA, InB, S, Out);
   input   InA, InB;
   input   S;
   output  Out;

   // YOUR CODE HERE
   wire    AS, notAS, notS, BS, notBS, result;
   not1 not0 (.in1(S), .out(notS));
   nand2 nand0 (.in1(InA), .in2(notS), .out(AS));
   nand2 nand1 (.in1(InB), .in2(S), .out(BS));
   not1 not1 (.in1(AS), .out(notAS));
   not1 not2 (.in1(BS), .out(notBS));
   nor2 nor0 (.in1(notAS), .in2(notBS), .out(result));
   not1 md (.in1(result), .out(Out));
   // assign Out = ~result;

endmodule
