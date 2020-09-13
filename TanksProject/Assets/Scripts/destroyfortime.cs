using UnityEngine;
using System.Collections;

public class destroyfortime : MonoBehaviour {
	public float time;
	// Use this for initialization
	void Start () {
		Destroy(this.gameObject, time);//经过一定时间销毁子弹爆炸实例；
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
