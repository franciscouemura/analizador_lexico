program Fatorial
var
	N, F: integer;
function Fat(N : integer) : integer;
begin
	if (N = 0) then
		begin
			Fat := 1;
		end;
	else
		begin
			Fat := N * Fat(N - 1);
		end;
end;
begin
	N := 0;
	read( N );
	if N >= 0 then begin
		F := Fat(N);
	end;
	write( F );
end.