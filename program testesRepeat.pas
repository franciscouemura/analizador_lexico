program testesRepeat
var
	x,y,z:integer;
begin
	x:=0;
	repeat
		write(x);
		write('');
		x:=x+1;
	until x = 3;
end.