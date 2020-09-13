using UnityEngine;
using System.Collections;

public class TankAttack : MonoBehaviour {

	// Use this for initialization
	public GameObject shellPerfab;//用来实例化子弹
	public KeyCode firekey = KeyCode.Space;//定义发射按键
	private Transform firePosition;//用于获取发射位置的那个
	public float shellSpeed = 15;
	public AudioClip shotAudio;
	void Start () {
		firePosition = transform.Find("FirePosition");//获取组件
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(firekey))
		{
			AudioSource.PlayClipAtPoint(shotAudio, transform.position);
			GameObject go = GameObject.Instantiate(shellPerfab, firePosition.position, firePosition.rotation) as GameObject;//实例化子弹 用.rotation保持旋转一至
			go.GetComponent<Rigidbody>().velocity = go.transform.forward * shellSpeed;
		}
	}
}
