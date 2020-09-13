using UnityEngine;
using System.Collections;

public class TankMovement : MonoBehaviour
{

	// Use this for initialization
	public float speed = 5;
	public float number = 1;//坦克编号 用来区分不同的坦克控制
	public float angularSpeed = 10;//旋转速度
	private Rigidbody rigidbody;
	public AudioClip adleAudio;
	public AudioClip drivingAudio;
	private AudioSource audio;
	void Start()
	{
		audio= this.GetComponent<AudioSource>();
		rigidbody = this.GetComponent<Rigidbody>();
	}

	// Update is called once per frame
	void FixedUpdate()
	{
		float v = Input.GetAxis("VerticalPlayer" + number);//获取垂直方向的按键；
		rigidbody.velocity = transform.forward * v * speed;

		float h = Input.GetAxis("HorizontalPlayer" + number);//获取水平方向的按键；
		rigidbody.angularVelocity = transform.up * h * angularSpeed;//添加旋转的速度，围绕Y轴
		if (Mathf.Abs(h) > 0.1 ||Mathf.Abs(v) > 0.1){
			audio.clip = drivingAudio;
			if(audio.isPlaying==false)
				audio.Play();

		}
		else
		{
			audio.clip = adleAudio;
			if (audio.isPlaying == false)
				audio.Play();
		}
	}
}
