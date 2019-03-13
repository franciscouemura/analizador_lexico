Program Fatorial
Var
	N, F: integer;
Function Fat(N : Integer) : Integer;
Begin
	If (N = 0) or (N = 1) then
		begin
			Fat := 1;
		End;
	Else
		Begin
			Fat := N * Fat(N - 1);
		End;
End;
Begin
	N := 0;
	read( N );
	If N >= 0 Then begin
		F := Fat(N);
	End;
	write( F );
End.
