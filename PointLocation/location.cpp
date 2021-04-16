#include <iostream>
#include <cmath>  
// 7
// 7 13 18 8 11 2 7 2 7 8 4 4 4 10

//5
//1 1 3 10 10 10 9 9 3 1

using namespace std;

double sgn(double val)
{
   return (val>0)?(1):((val<0)?(-1):(0));
}

void WherePoint(double Px, double Py, int n, double X[], double Y[]){
	double angle[n], summ = 0, PxV, PyV, PxV1, PyV1;
	for (int i = 1; i < n; i++){
		PxV = X[i-1] - Px;
		PyV = Y[i-1] - Py;
		PxV1 = X[i] - Px;
		PyV1 = Y[i] - Py;
		angle[i] = acos((PxV*PxV1 + PyV*PyV1)/(sqrt(PxV*PxV+PyV*PyV)*sqrt(PxV1*PxV1+PyV1*PyV1)))*sgn(PxV*PyV1 - PxV1*PyV);
		summ+=angle[i];
		cout << angle[i]<< endl;
	}
	cout << endl <<"summ = "<< summ << endl;
	if(summ>=1 || summ <= -1) cout << "inside";
		else if(summ < 1 && summ > -1) cout << "outside"; 
			else cout << "is vertix";

}

int main(){
	int n;
	cin >> n;
	double X[n], Y[n], Px, Py;

	cout << "Enter the vertixes of the poligon(successively)"<< endl;
	for (int i = 0; i < n; i++){
		cin >> X[i] >> Y[i];
	}

	cout << "Enter the point(x, y)" << endl;
	cin >> Px >> Py;

	WherePoint( Px,  Py,  n,  X,  Y);
}