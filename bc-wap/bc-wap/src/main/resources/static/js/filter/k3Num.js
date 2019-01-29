function K3Num(one,two,three){
	this.one = one;
	this.two = two;
	this.three = three;
	
	
		var temp = 0;
	
		if(this.one > this.two){
			temp = this.one;
			this.one = this.two;
			this.two = temp;
		}
		
		if(this.two > this.three){
			temp = this.two;
			this.two = this.three;
			this.three = temp;
		}
		
		if(this.one > this.two){
			temp = this.one;
			this.one = this.two;
			this.two = temp;
		}
	
}